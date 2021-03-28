<template>
  <v-layout align-center justify-center>
    <v-card flat width="1400" style="background-color: transparent;">
      <v-container>
        <v-row class="my-10 mx-15 px-10">
          <v-col v-for="(flag, name) in flags" :key="name" align="center">
            <v-btn icon width="140" height="140">
              <v-avatar class="mr-2" size="140">
                <img :src="require(`@/assets/images/flags/${flag.icon}`)">
              </v-avatar>
            </v-btn>
          </v-col>
        </v-row>
        <v-row
          v-for="m in 2"
          :key="m"
        >
          <v-col
            v-for="n in 3"
            :key="n"
          >
            <v-card
              color="white"
              rounded="xl"
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <v-list-item-title class="headline mb-1">
                    Animals
                  </v-list-item-title>
                  <v-list-item-subtitle>동물 15cards</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="80"
                  color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn
                  outlined
                  rounded
                  color="warning"
                >
                  게임하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-layout>
  
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
      ],
    }
  },
}
</script>

<style>

</style>