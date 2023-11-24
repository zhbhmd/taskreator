<template>
  <div>
    <NavigationBar />
    <SpinningLoader v-if="$store.state.loading" />
    <main>
      <Nuxt />
    </main>
  </div>
</template>
<script>
export default {

}
</script>
