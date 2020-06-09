<template>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="Id">
            <el-input v-model="ruleForm.id" readOnly></el-input>
        </el-form-item>

        <el-form-item label="Book Name" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="Author" prop="author">
            <el-input v-model="ruleForm.author"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">Update</el-button>
            <el-button @click="resetForm('ruleForm')">Reset</el-button>
        </el-form-item>

    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    id: '',
                    name: '',
                    author: ''
                },
                rules: {
                    name: [
                        { required: true, message: 'Book name can not be empty', trigger: 'blur' }
                    ],
                    author:[
                        { required: true, message: 'Author can not be empty', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/book/update',this.ruleForm).then(function(resp){
                            if(resp.data == 'success'){
                                _this.$alert('《'+_this.ruleForm.name+'》update successfully ', 'news', {
                                    confirmButtonText: 'sure',
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
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findById/'+this.$route.query.id).then(function(resp){
                _this.ruleForm = resp.data
            })
        }
    }
</script>