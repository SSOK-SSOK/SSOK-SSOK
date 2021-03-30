<template>
  <div>
    <audio-recorder
      upload-url="YOUR_API_URL"
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