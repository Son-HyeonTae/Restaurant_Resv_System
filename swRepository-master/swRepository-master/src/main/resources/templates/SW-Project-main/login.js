const router = require('express').Router();
const path = require('path');
// 솔직히 이 부분은 
router.post('/login',(req,res)=>{
    const mysql = require('../database')(); // DB 연결
    const connection = mysql.init();
    mysql.db_open(connection);
    const userid = req.body.id;
    const userpw = req.body.password;
    connection.query('SELECT * FROM USERS WHERE id = ? AND pw = ? ',[userid, userpw], (err, results, fields) =>{ //조건문 DB에서 ID,PW를 찾음
        if (err){
            throw err;
        }else if (results.length>0){
            res.send('<script type="text/javascript">alert("회원님, 환영합니다!"); document.location.href="after_index.html" </script>');
        }else{
            res.send('<script>alert("로그인 정보가 일치하지 않습니다. 다시 확인해주세요."); document.location.href="login_signup.html";</script>');
        }
        connection.end(); // DB 연결 끊기
    });  
})
module.exports = router;