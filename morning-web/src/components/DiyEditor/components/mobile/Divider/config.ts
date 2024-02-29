import { DiyComponent } from '@/components/DiyEditor/util'

/** 分割线属性 */
export interface DividerProperty {
  // 高度
  height: number
  // 线宽
  lineWidth: number
  // 边距类型
  paddingType: 'none' | 'horizontal'
  // 颜色
  lineColor: string
  // 类型
  borderType: 'solid' | 'dashed' | 'dotted' | 'none'
}

// 定义组件
export const component = {
  id: 'Divider',
  name: '分割线',
  icon: 'tdesign:component-divider-vertical',
  property: {
    height: 30,
    lineWidth: 1,
    paddingType: 'none',
    lineColor: '#dcdfe6',
    borderType: 'solid'
  }
} as DiyComponent<DividerProperty>
