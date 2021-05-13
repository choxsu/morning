<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title"  size="small" placeholder="请输入标题" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
<!--      <el-select v-model="listQuery.importance" placeholder="Imp" clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="listQuery.type" placeholder="Type" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>-->
      <el-button class="filter-item" size="small" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" size="small" style="margin-left: 10px;" type="primary" icon="el-icon-plus" @click="handleCreate">
        新增
      </el-button>
      <el-button :loading="downloadLoading"  size="small" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="作者" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览次数" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.click_count }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="类目" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.category_id | categoryFilter }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status | statusFilterToStr}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="carete_time" label="发布时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.create_at }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="small" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button v-if="row.status!='published'" size="small" type="success" @click="handleModifyStatus(row,'published')">
            发布
          </el-button>
          <el-button v-if="row.status!='draft'" size="small" @click="handleModifyStatus(row,'draft')">
            草稿
          </el-button>
          <el-button v-if="row.status!='deleted'" size="small" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > listQuery.limit" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetchData" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="类别" prop="categoryId">
          <el-select v-model="temp.categoryId" class="filter-item" placeholder="请选择">
            <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="temp.status" class="filter-item" placeholder="请选择">
            <el-option v-for="item in statusOptions" :key="item.status" :label="item.desc" :value="item.status" />
          </el-select>
        </el-form-item>

        <el-form-item label="内容">
          <el-input v-model="temp.content" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="Please input" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确认
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getArticleList,createArticle } from '@/api/news'
import Pagination from '@/components/Pagination'
const calendarTypeOptions = [
  { key: '1', display_name: '产品动态' },
  { key: '2', display_name: '公司动态' }
]

const statusOptions = [
  { status: 'published', color: "success",  "desc": "发布"},
  { status: 'draft', color: "gray",  "desc": "草稿"}
]

const statusShowOptions = [
  { status: 'published', color: "success",  "desc": "发布"},
  { status: 'draft', color: "gray",  "desc": "草稿"},
  { status: 'deleted', color: "danger", "desc": "删除"}
]

const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

const statusColorValue = statusShowOptions.reduce((acc, cur) => {
  acc[cur.status] = cur.color
  return acc
}, {})


const statusDescValue = statusShowOptions.reduce((acc, cur) => {
  acc[cur.status] = cur.desc
  return acc
}, {})

export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      return statusColorValue[status]
    },
    statusFilterToStr(status) {
      return statusDescValue[status]
    },
    categoryFilter(categoryId) {
      return calendarTypeKeyValue[categoryId]
    }
  },
  data() {
    return {
      calendarTypeOptions,
      statusOptions,
      statusShowOptions,
      total: 0,
      list: [],
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      downloadLoading: false,
      temp: {
        id: undefined,
        content: '',
        title: '',
        status: 'published',
        categoryId: ''
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      },
      rules: {
        categoryId: [{ required: true, message: '类别必须选择', trigger: 'change' }],
        title: [{ required: true, message: '标题必须填写', trigger: 'blur' }]
      },
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getArticleList(this.listQuery).then(response => {
        if (response.data.success) {
          let data = response.data;
          this.total = data.data.total | 0
          this.list = data.data.items
        }
        this.listLoading = false
      }).finally(() => {
        this.listLoading = false
      })
    },
    handleFilter() {
      this.fetchData()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        content: '',
        title: '',
        status: 'published',
        categoryId: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleDownload() {
      this.$notify.success({title: "提示",message: "导出"})
    },
    handleUpdate(row) {
      console.log(row)
    },
    handleModifyStatus(row, status){
      console.log(row, status)
    },
    handleDelete(row){
      console.log(row)
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.temp.author = 'vue-element-admin'
            createArticle(this.temp).then((response) => {
              this.dialogFormVisible = false
              this.$notify({
                title: 'Success',
                message: response.data.msg,
                type: 'success',
                duration: 2000
              })
              this.fetchData()
            })
          }
      })
    },
    updateData() {
      this.$message.success("更新")
    }

  }
}
</script>
