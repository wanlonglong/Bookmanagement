<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 43%">
            <el-table-column
                    fixed
                    prop="id"
                    label="ID"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="Book Name"
                    width="142">
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="Author"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="Operation"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">update</el-button>
                    <el-button @click="deleteBook(scope.row)" type="text" size="small">delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        methods: {
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/book/deleteById/'+row.id).then(function(resp){
                    _this.$alert('《'+row.name+'》delete successfully！', 'news', {
                        confirmButtonText: 'Sure',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                this.$router.push({
                    path: '/update',
                    query:{
                        id:row.id
                    }
                })
            },
            page(currentPage){
                const _this = this
                axios.get('http://localhost:8181/book/findAll/'+(currentPage-1)+'/4').then(function(resp){
                    console.log(resp)
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })
            }
        },

        data() {
            return {
                pageSize:'1',
                total:'11',
                tableData: [{
                    id: 1,
                    name: 'bookshop',
                    author: 'haruki'
                }, {
                    id: 2,
                    name: 'grate man',
                    author: 'leo'
                }, {
                    id: 3,
                    name: 'living',
                    author: 'konan'
                }]
            }
        },

        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findAll/0/4').then(function(resp){
                console.log(resp)
                _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements
            })
        }
    }
</script>