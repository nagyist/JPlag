import { describe, expect, it } from 'vitest'
import { mount } from '@vue/test-utils'
import MetricSelector from '@/components/optionsSelectors/MetricSelector.vue'
import { MetricJsonIdentifier, MetricTypes } from '@/model/MetricType'

describe('OptionSelectorComponent', () => {
  it('renders all options', async () => {
    const wrapper = mount(MetricSelector, {
      props: {
        title: 'Test:'
      }
    })

    expect(wrapper.text()).toContain('Test:')
    for (const metric of MetricTypes.METRIC_LIST) {
      expect(wrapper.text()).toContain(metric.longName)
    }
  })

  it('renders given metrics only', async () => {
    const wrapper = mount(MetricSelector, {
      props: {
        title: 'Test:',
        metrics: [MetricJsonIdentifier.AVERAGE_SIMILARITY, MetricJsonIdentifier.MINIMUM_SIMILARITY]
      }
    })

    expect(wrapper.text()).toContain('Test:')
    expect(wrapper.text()).toContain('Average')
    expect(wrapper.text()).toContain('Minimum')
    expect(wrapper.text()).not.toContain('Maximum')
  })

  it('switch selection', async () => {
    const wrapper = mount(MetricSelector, {
      props: {
        title: 'Test:'
      }
    })

    await wrapper.findAllComponents({ name: 'OptionComponent' })[1].trigger('click')

    expect(wrapper.emitted('selectionChanged')).toBeTruthy()
    expect(wrapper.emitted('selectionChanged')?.length).toBe(1)
    expect(wrapper.emitted('selectionChanged')?.[0]).toEqual([
      MetricJsonIdentifier.MAXIMUM_SIMILARITY
    ])
  })
})
