const rumorRouter = {
    path: '/rumor',
    name: 'Rumor',
    redirect: '/rumor/list',
    children: [
        {
            path: 'list',
            name: 'List',
            component: () => import('@/views/rumor/list/index'),
            meta: {
                title: '辟谣首页'
            }
        },
        {
            path: 'detail',
            name: 'Detail',
            component: () => import('@/views/rumor/detail/index'),
            meta: {
                title: '辟谣详情'
            }
        }
    ]
}

export default rumorRouter