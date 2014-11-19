var express 			= require('express'),
	misc 				= require('./routes/misc'),
	users 				= require('./routes/users'),
	searchUsers			= require('./routes/searchUsers'),
	searchUserByEmail 	= require('./routes/searchUserByEmail'),
	bands				= require('./routes/bands'),
   	dummy 				= require('./routes/dummy');
   	//misc = require('./routes/misc');

var app = express();

app.set('port', process.env.PORT || 8888);
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);

app.configure('development', function () {
    app.use(express.errorHandler());
});

app.get('/ping',misc.ping);
app.get('/users/:userId', users.get);
app.get('/users/search/:admin', searchUsers.get);

app.get('/users/searchByEmail/:email', searchUserByEmail.get);

app.get('/bands/:bandId',bands.get);


//app.get('/catalog',catalog.get);
//app.put('/catalog/:cataloId?',catalog.active)


app.get('/dummy',dummy.getDummyInfo);


//
// 404 
//
app.all('*', misc.notFound);


app.listen(app.get('port'), function(){
	console.log("Mock maxibandas server listening on port " + app.get('port'));
});

