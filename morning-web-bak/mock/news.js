const Mock = require('mockjs')

const data = Mock.mock({
  'items|150': [{
    "id|+1": 2,
    title: '@ctitle(10, 50)',
    category_id: 1,
    category: '企业类目',
    'status|1': ['published', 'draft', 'deleted'],
    author: '苏小秋',
    display_time: '@datetime',
    pageviews: '@integer(300, 10000)'
  }]
})

module.exports = [
  {
    url: '/morning-web/post/list',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 1,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
