	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import chukujilu from '@/views/chukujilu/list'
	import yuangong from '@/views/yuangong/list'
	import shouzhimingxi from '@/views/shouzhimingxi/list'
	import kaoqinjilu from '@/views/kaoqinjilu/list'
	import syslog from '@/views/syslog/list'
	import yaopinxinxi from '@/views/yaopinxinxi/list'
	import rukujilu from '@/views/rukujilu/list'
	import users from '@/views/users/list'
	import yaopindingdan from '@/views/yaopindingdan/list'
	import systemintro from '@/views/systemintro/list'
	import yaopinleixing from '@/views/yaopinleixing/list'
	import config from '@/views/config/list'
	import usersCenter from '@/views/users/center'
	import yuangongCenter from '@/views/yuangong/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/yuangongCenter',
			name: '员工个人中心',
			component: yuangongCenter
		}
		,{
			path: '/news',
			name: '新闻公告',
			component: news
		}
		,{
			path: '/chukujilu',
			name: '出库记录',
			component: chukujilu
		}
		,{
			path: '/yuangong',
			name: '员工',
			component: yuangong
		}
		,{
			path: '/shouzhimingxi',
			name: '收支明细',
			component: shouzhimingxi
		}
		,{
			path: '/kaoqinjilu',
			name: '考勤记录',
			component: kaoqinjilu
		}
		,{
			path: '/syslog',
			name: '操作日志',
			component: syslog
		}
		,{
			path: '/yaopinxinxi',
			name: '药品信息',
			component: yaopinxinxi
		}
		,{
			path: '/rukujilu',
			name: '入库记录',
			component: rukujilu
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/yaopindingdan',
			name: '药品订单',
			component: yaopindingdan
		}
		,{
			path: '/systemintro',
			name: '网站介绍',
			component: systemintro
		}
		,{
			path: '/yaopinleixing',
			name: '药品类型',
			component: yaopinleixing
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
