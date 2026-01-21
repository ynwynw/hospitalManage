// components/musicPlayer/musicPlayer.js
Component({
    properties: {
        tableName:{
            type: String,
            value: '',    
        },
        playlist: {
        type: Array,
        value: [],
        observer(){
            if (this.properties.playlist.length > 0) {
                this.audioCtx = wx.createInnerAudioContext();
                this.initAudioListeners();
         console.log('加载  ', this.properties.playlist);
    this.playMusic(this.data.playIndex);

            }
        }
      }
    },
data: {
isAuthStatus:false,
curHeight:"90vh",
musicPlayShow: false,
initClick: true,
randomPlayMusic: false, // 控制是否随机播放
playMode: 'sequential', // 播放模式：sequential（顺序播放），shuffle（随机播放） single  （单曲循环） 
baseURL: wx.getStorageSync('baseURL')+'/' || '', // 确保baseURL有默认值
item: 0,
tab: 0,
isPlayingMusic: false,
state: 'paused',
playIndex: 0,
play: {
    currentTime: '00:00',
    duration: '00:00',
    percent: 0,
    title: '',
    singer: '',
    coverImgUrl: '',
},
imgUrls: [],
indicatorDots: true,
autoplay: true,
interval: 3000,
duration: 500,
isAuthStatus: false, // 假设有一个认证状态，需要在适当位置更新
},

  methods: {
    async detailBtn(e) {
        console.log('item',item);
        const item = e.currentTarget.dataset.item;
        getApp().globalData.detailId = item?.id
        getApp().globalData.detailList =item;
        const isAuthStatus=wx.getStorageSync('isAuthStatus')
        wx.navigateTo({
            url: `/pages/${this.data.tableName}/detail?isAuth=${isAuthStatus}`
        })
        if(isAuthStatus) {
            wx.setStorageSync('isAuth', true)
        }
        },
  // 处理点击切换歌曲
  changeitem(e) {
    this.setData({
      item: e.target.dataset.item
    });
  },
  // 点击顺序播放
  sequentialPlayback() {
    this.setData({
      playMode: 'sequential',
      randomPlayMusic: false,
    });
    wx.showToast({
      title: '列表播放',
      icon:'none'
    })
    // this.playNextMusic();
  },
//   单曲循环
singlePlayback() {
    this.setData({
      playMode: 'single',
    });
    wx.showToast({
        title: '单曲循环',
        icon:'none'
      })
  },
  // 点击随机播放
  shuffle() {
    this.setData({
      playMode: 'shuffle',
      randomPlayMusic: true,
    });
    wx.showToast({
        title: '随机播放',
        icon:'none'
      })
    // this.playRandomMusic();
  },
  
  // 点击播放列表中的歌曲
  async playListTap(e) {
    const index = e.currentTarget.dataset.index;
    if (this.data.state === 'running') {
      this.pause();
    }
    this.setData({
      musicPlayShow: true,
      curHeight:"70vh",
      playIndex: index,
      initClick: false,
      state: 'paused',
    });
    this.playMusic(index);
  },
  fatherMethod(){
      this.triggerEvent('myEvent')
  },
  // 切换tab
  changetab(e) {
    const { current } = e.detail;
    this.setData({
      musicPlayShow: current !== 1,
      tab: current,
    });
  },
// 设置当前播放的歌曲并播放
playMusic(index) {
    // 如果是随机播放，则随机选择一个索引
    if (this.data.playMode === 'shuffle') {
        index = Math.floor(Math.random() * this.data.playlist.length);
    }
    // 设置当前音乐
    this.setMusic(index);
    // 如果当前状态不是running（即不是正在播放），则播放音乐
    if (this.data.state !== 'running') {
        this.play();
    }
    // 注意：这里不需要else来暂停音乐，因为onEnded事件触发时状态已经是paused了
},
next: function() {
var index = this.data.playIndex >= this.data.playlist.length - 1 ? 0 : this.data.playIndex + 1
this.setMusic(index)
if (this.data.state === 'running') {
this.play()
}
},
// 播放下一首歌（根据播放模式）
playNextMusic() {
let index = this.data.playIndex;
    if (this.data.playMode === 'shuffle') {
    index = Math.floor(Math.random() * this.data.playlist.length);
}else if (this.data.playMode === 'single') {
    this.playListTap(index)
    return 
}
this.playMusic(index);
},
// 随机播放一首歌
playRandomMusic() {
const index = Math.floor(Math.random() * this.data.playlist.length);
this.playMusic(index);
},
// 设置当前播放的歌曲
setMusic(index) {
const music = this.data.playlist[index];
if (music) {
this.audioCtx.src = this.data.baseURL + music.src;
this.setData({
playIndex: index,
'play.title': music.title,
'play.singer': music.singer,
'play.coverImgUrl': music.coverImgUrl,
'play.currentTime': '00:00',
'play.duration': '00:00',
'play.percent': 0,
});
}
},
// 播放音乐
play() {
this.audioCtx.play();
this.setData({ state: 'running' });
},
// 暂停音乐
pause() {
this.audioCtx.pause();
this.setData({ state: 'paused' });
},
// 初始化音频监听器
initAudioListeners() {
const that = this;
this.audioCtx.onError(() => {
    console.log('播放失败:' + that.audioCtx.src);
});
this.audioCtx.onEnded(() => {
    if (that.data.playMode === 'sequential') {
        that.next();
    } else if (that.data.playMode === 'single') {
        // 单曲循环：重置当前时间并重新播放
        this.play()
    } else { // 随机播放
        let newIndex;
        do {
            newIndex = Math.floor(Math.random() * that.data.playlist.length);
        } while (newIndex === that.data.playIndex); // 尝试避免重复（但不一定总是成功）
        console.log('newIndex',newIndex);
        that.setMusic(newIndex);
        that.play();
    }
});
this.audioCtx.onTimeUpdate(() => {
that.setData({
    'play.duration': that.formatTime(that.audioCtx.duration),
    'play.currentTime': that.formatTime(that.audioCtx.currentTime),
    'play.percent': (that.audioCtx.currentTime / that.audioCtx.duration) * 100,
    });
});
},
// 格式化时间
formatTime(time) {
const minute = Math.floor(time / 60) % 60;
const second = Math.floor(time % 60);
return `${minute < 10 ? '0' + minute : minute}:${second < 10 ? '0' + second : second}`;
},
// 进度条拖动事件
sliderChange(e) {
const second = e.detail.value * this.audioCtx.duration / 100;
this.audioCtx.seek(second);
this.setData({
    'play.currentTime': this.formatTime(second),
    'play.percent': e.detail.value,
});
},
// 进度条拖动中事件
sliderChanging(e) {
console.log(e.detail.value);
}
  }
  });