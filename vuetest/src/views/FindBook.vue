<template>


    <div>
        <el-table
                :data="tableData"
                border
                style="width: 15%" v-if="!flag1">

            <el-table-column
                    align="center"

                    prop="name"
                    label="Book Name"
                    width="142">
            </el-table-column>
        </el-table>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">



        <el-form-item label="Author" prop="author" v-if="flag1">
            <el-input v-model="ruleForm.author"></el-input>
        </el-form-item>


        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')" v-if="flag1">Search</el-button>
            <el-button @click="resetForm('ruleForm')" v-if="flag1">Reset</el-button>
        </el-form-item>

    </el-form>
    </div>

</template>

<script>
    export default {
        data() {
            return {

                flag1:true,

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
                }],

                ruleForm: {
                    name: '',
                    author: ''
                },
                rules: {
                    name: [
                        { required: true, message: 'Book Name can not be empty', trigger: 'blur' }
                    ],
                    author:[
                        { required: true, message: 'Author can not be empty', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {


            submitForm(formName) {
                this.flag1=false;
                const _this = this;
                console.log(this.ruleForm.author);
                var tmpname= this.ruleForm.author;

                axios.get('http://localhost:8181/book/findByAuthor/'+(tmpname)).then(function(resp){
                        console.log(resp);
                        if(resp.data.length!=0){_this.tableData = resp.data}

                    })

            },

            submitForm1111(formName){

                flag1=false;

                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/book/save',this.ruleForm).then(function(resp){
                            console.log(resp)
                            if(resp.data == 'success'){
                                _this.$alert('《'+_this.ruleForm.name+'》add successfully！', 'news', {
                                    confirmButtonText: 'Sure',
                                    callback: action => {
                                        _this.$router.push('/BookManage')
                                    }
                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

