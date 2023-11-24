<template>
  <ListingTab />
</template>

<script>

export default {
  beforeCreate () {
    console.log(this.$store.state.loading)
  },
  mounted () {
    console.log(this.$store.state.loading)
  }
}
</script>
