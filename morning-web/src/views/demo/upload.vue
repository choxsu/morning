<!-- 文件上传组件(单图+多图)示例 -->
<script setup lang="ts">
const singlePicUrl = ref(
  "https://assets.styc.cc/20240307/176210e4694f4a7f8d63d43fed0a4f29.jpg"
);
// 这里放外链图片，防止被删
const multiPicUrls = ref([
  "https://assets.styc.cc/20240307/22eec2fc7f69462f8a9bcec66651f474.jpg",
  "https://assets.styc.cc/images/202303281437652.png",
  "https://assets.styc.cc/images/202303281440060.png",
  "https://assets.styc.cc/images/202303281443478.png",
  "https://assets.styc.cc/images/202304161642554.png",
  "https://assets.styc.cc/20240307/318f524993e5472b8d5d6f04efd56547.jpg",
  "https://assets.styc.cc/20240307/efaae2ae580c4801a00a8780299780f2.jpg",
]);
</script>
<template>
  <div class="app-container">
    <el-form>
      <el-form-item label="单图上传">
        <SingleUpload v-model="singlePicUrl" />
      </el-form-item>
      <el-form-item label="多图上传">
        <MultiUpload v-model="multiPicUrls" />
      </el-form-item>
    </el-form>
  </div>
</template>
