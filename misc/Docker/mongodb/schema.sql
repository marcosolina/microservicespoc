use marcosecurity


db.createCollection("resources")
db.resources.createIndex({"_id": 1}, {unique: true})


db.resources.insertMany([
{
	_id: "app",
	resource: "/app",
	roles: ["admin","user"]
},
{
        _id: "admin",
	resource: "/admin",
	roles: ["admin"]
}])


