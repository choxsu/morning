const Mock = require('mockjs')

const data = Mock.mock({
  'items|300': [{
    id: '@id',
    title: '@sentence(10, 20)',
    'status|1': ['published', 'draft', 'deleted'],
    author: '苏小秋',
    display_time: '@datetime',
    pageviews: '@integer(300, 5000)'
  }]
})

module.exports = [
  {
    url: '/morning-web/table/list',
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
