const axios = require('axios')
const fs = require('fs')

const getImages = async (page = 2) => {
    try {
        
        const { data } = await axios.get(`https://www.pexels.com/en-us/api/v3/search/photos?page=${page}&per_page=1000&query=mobile%20wallpaper&orientation=all&size=all&color=all`,
        {
            cookie: 'search_exp=1xuXsaszTP2mdx7T9XkUzw|2; ab.storage.deviceId.5791d6db-4410-4ace-8814-12c903a548ba=%7B%22g%22%3A%22dc9ee94b-f992-0f64-258f-c80157b40b17%22%2C%22c%22%3A1653465299513%2C%22l%22%3A1653465299513%7D; ab.storage.sessionId.5791d6db-4410-4ace-8814-12c903a548ba=%7B%22g%22%3A%229ecb0a8e-3613-53e5-ee45-8e8d97187c9e%22%2C%22e%22%3A1653467104660%2C%22c%22%3A1653465299503%2C%22l%22%3A1653465304660%7D; __cf_bm=ZsKRVCC6n03YlkEbx1l0o5HwX66x6uUPzzL47uoj8FM-1653633808-0-AR+L9NzID+sjSIYmGXDx9quAmueHL+I6BqO5MJfXLPuZDocKG6Cg3xE1zoyduFf8DUslbDxXRibnZQlAjQPqKDIM2hYii+C0MsePV43EBz0AXEq6l9VjTwof6/S3VIlejFm0XKjHALkmugTxIlNqWJLFsVpzjV4/x1pzSuUb1U3f',
            headers: {
                'secret-key': 'H2jk9uKnhRmL6WPwh89zBezWvr'
            }
        })
        const db =[]
        data.data.forEach(element => {

           db.push({
               id: element.id,
               alt: element.attributes.alt,
               created_at: element.attributes.created_at,
               description: element.attributes.description,
               height: element.attributes.height,
               width: element.attributes.width,
               tags: element.attributes.tags,
               title: element.attributes.title,
               slug: element.attributes.slug,
               image: element.attributes.image,
           })
        });
        return db;
    } catch (error) {
        console.log(error.response.data)
        console.log('==================')
        console.error('ERROR AT: ' + page)
        console.log('==================')
        return db;
    }
}

(async () => {
    const total = []
    for (let i = 1; i<=8; i+=1 ) {
        const data = await getImages(i)
        console.log('==================')
        total.push(data)
        console.log('CRAWLED PAGE: ' + i)
        console.log('CONTAINS: ' + data.length)
        console.log('==================')

    }
    fs.writeFileSync('./db.json', JSON.stringify(total))
})()


