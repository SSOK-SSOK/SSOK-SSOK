<template>
  <div>
    <audio-recorder
      upload-url="@/assets"
      :attempts="3"
      :time="2"
    />
  </div>
</template>

<script>
export default {
  name: "Test",
  methods: {
  }
}
</script>

<style>

</style>