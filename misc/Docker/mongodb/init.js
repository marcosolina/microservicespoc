reactui = db.getSiblingDB('reactui')

reactui.createCollection('resources')

reactui.resources.createIndex({ resource: 1 }, { unique: true })

reactui.resources.insertMany([
{
	_id: 'app',
	resource: '/home',
	roles: ['admin', 'user']
},
{
	_id: 'admin',
	resource: '/app',
	roles: ['admin']
}])


