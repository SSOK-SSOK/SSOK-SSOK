<template>
  <v-row mx-10>
    <v-col v-for="(flag, name) in flags" :key="name" align="center">
      <v-btn icon>
        <v-avatar size="140" class="mr-2">
          <img :src="require(`@/assets/images/flags/${flag.icon}`)">
        </v-avatar>
      </v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'CardCategory',
  data: function () {
    return {
      flags: [
        { name: "ko", icon: "kr.png"},
        { name: "us", icon: "us.png"},
        { name: "cn", icon: "cn.png"},
        { name: "jp", icon: "jp.png"},
        { name: "fr", icon: "fr.png"},
        { name: "es", icon: "es.png"},
        { name: "vn", icon: "vn.png"}
      ]
    }
  }
}
</script>

<style>

</style>