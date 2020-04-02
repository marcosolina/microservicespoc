use marcosecurity
db.createCollection("Users")
db.Users.createIndex({"userName": 1}, {unique: true})

db.createCollection("OauthClients")
db.Users.createIndex({"client_id": 1}, {unique: true})


db.Users.insertOne({
	"userName": "marco",
	"password": "$2a$10$tN8.lMKCERHxwmg2XJI3g.QJLR0dW956QY/uJywEvVgm9tbUquJDy",
	"authorities": ["ADMIN", "GUEST"]
})



db.OauthClients.insertOne({
	"client_id":"marcoclient",
	"client_secret": "$2a$10$gMPzajSBU46wpZyTE99X.uhUWdYex6YQgH2o4pCUG5CK9U8xp0VPa",
	"scopes": ["read", "write", "trust"],
	"authorized_grant_types": ["password", "refresh_token", "authorization_code", "implicit"],
	"access_token_validity": 120,
	"refresh_token_validity": 120
})