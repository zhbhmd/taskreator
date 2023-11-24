<template>
  <div class="bg-white p-4 border-b border-gray-300 flex" :class="{ 'bg-green-100': data?.status === 'DONE' }">
    <div class="w-4/5">
      <span class=" text-xl font-semibold text-gray-800">
        {{ data?.title }}
      </span>
      <span v-if="data?.date || data?.time " class="text-slate-500 ml-4 text-xs font-semibold">Due on</span>
      <span class="text-slate-500 ml-4 text-xs font-semibold">{{ data?.date }}</span>
      <span class="text-slate-500 ml-4 text-xs font-semibold">{{ data?.time }}</span>
      <p class="text-gray-600 mt-2">
        {{ data?.description }}
      </p>
    </div>
    <div class="w-1/5 flex items-center justify-end">
      <input type="checkbox" :disabled="data?.status === 'DONE'" :checked="data?.status === 'DONE'" class="form-checkbox h-6 w-6 text-blue-500" @click="() => taskCompleted(data.id)">
    </div>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Object,
      default: () => {}
    }
  },
  mounted () {
    // log  prop
    console.log('Received prop:', this.data)
  },

  methods: {
    taskCompleted (data) {
      console.log('checked')
      // Emit an event named 'custom-event' to the parent
      this.$emit('checked', data)
    }
  }
}
</script>
