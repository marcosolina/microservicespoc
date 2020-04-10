use marcosecurity
db.createCollection("users")
db.users.createIndex({"userName": 1}, {unique: true})

db.createCollection("oauthClients")
db.oauthClients.createIndex({"_id": 1}, {unique: true})

db.createCollection("tokenStore")
db.tokenStore.createIndex({"_id": 1}, {unique: true})

db.createCollection("refreshStore")
db.refreshStore.createIndex({"_id": 1}, {unique: true})

db.createCollection("resources")
db.resources.createIndex({"resource": 1}, {unique: true})

db.users.insertOne({
	"userName": "marco",
	"password": "$2a$10$tN8.lMKCERHxwmg2XJI3g.QJLR0dW956QY/uJywEvVgm9tbUquJDy",
	"authorities": ["GUEST"]
})



db.oauthClients.insertOne({
	"clientId":"marcoclient",
	"clientSecret": "$2a$10$gMPzajSBU46wpZyTE99X.uhUWdYex6YQgH2o4pCUG5CK9U8xp0VPa",
	"scope": ["read", "write", "trust"],
	"authorizedGrantTypes": ["password", "refresh_token", "authorization_code", "implicit"],
	"accessTokenValiditySeconds": 120,
	"refreshTokenValiditySeconds": 120
})

db.resources.insertMany([
{
	resource: "/all",
	authorities: ["ADMIN","GUEST"]
},
{
	resource: "/admin",
	authorities: ["ADMIN"]
}])


