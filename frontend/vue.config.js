const path = require('path')

module.exports = {
  lintOnSave: false,

  transpileDependencies: ["vuetify"],

  css: {
    loaderOptions: {
      sass: {
        includePaths: [path.resolve(__dirname, './node_modules/compass-mixins/lib')]
      }
    }
  }
};
