var express = require('express')
var compression = require('compression')

var port = 3050
var app = express()

app.use(compression())
app.use(express.static('./dist'))

module.exports = app.listen(port, function(err){
    if(err){
        console.log(err)
        return
    }
    console.log('htpp://localhost:' + port)
})