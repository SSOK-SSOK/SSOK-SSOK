<template>
  <div>
    <audio-recorder
      upload-url="https://j4a201.p.ssafy.io/card-api/file/upload"
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