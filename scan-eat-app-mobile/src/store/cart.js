// 支持相同菜不同口味
import {defineStore} from "pinia";

export const useCartStore = defineStore('cart', {
    state: () => ({
        items: [] // 每项包含菜品信息 + options
    }),
    getters: {
        totalCount: state => state.items.reduce((sum, i) => sum + i.count, 0),
        totalPrice: state => state.items.reduce((sum, i) => sum + i.count * i.price, 0)
    },
    actions: {
        addItem(dishWithOptions) {
            const key = `${dishWithOptions.id}-${dishWithOptions.spiceLevel}-${dishWithOptions.coriander}`
            const existing = this.items.find(i => i._key === key)
            if (existing) {
                existing.count += dishWithOptions.count
            } else {
                this.items.push({ ...dishWithOptions, _key: key })
            }
        }
    }
})
