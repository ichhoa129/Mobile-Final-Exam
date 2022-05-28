const db = require('./db.json')
const fs = require('fs')


const newData = []

db.forEach(element => {
    newData.push(...element)
});

fs.writeFileSync('./db2.json', JSON.stringify(newData))
