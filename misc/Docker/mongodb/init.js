/*
 * React UI Project
 */
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


reactui.createCollection('webclients')

reactui.webclients.insertMany([
	{
		_id: "dishes-service",
		clientId: "reactui-service",
		clientSecret: "091dab56-57af-497e-b7d3-18e8496a7049",
		authorizationGrantType: "client_credentials",
		clientAuthenticationMethod: "post",
		tokenUri: "http://localhost:8091/auth/realms/marco-realm/protocol/openid-connect/token"
	},
	{
		_id: "prices-service",
		clientId: "reactui-service",
		clientSecret: "091dab56-57af-497e-b7d3-18e8496a7049",
		authorizationGrantType: "client_credentials",
		clientAuthenticationMethod: "post",
		tokenUri: "http://localhost:8091/auth/realms/marco-realm/protocol/openid-connect/token"
	}
	
])

/*
 * Prices Service Project
 */
pricesservice = db.getSiblingDB('pricesservice')

pricesservice.createCollection('webclients')

pricesservice.webclients.insertMany([
	{
		_id: "dishes-service",
		clientId: "prices-service",
		clientSecret: "6cb1fea2-e3c5-4c93-b141-b4cb4e046f5f",
		authorizationGrantType: "client_credentials",
		clientAuthenticationMethod: "post",
		tokenUri: "http://localhost:8091/auth/realms/marco-realm/protocol/openid-connect/token"
	}
])