marcodb = db.getSiblingDB('marcosecurity')

marcodb.createCollection('resources')

marcodb.resources.createIndex({ resource: 1 }, { unique: true })

marcodb.resources.insertMany([
{
	_id: 'app',
	resource: '/app',
	roles: ['admin', 'user']
},
{
	_id: 'admin',
	resource: '/admin',
	roles: ['admin']
}])


