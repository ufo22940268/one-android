all:
	mvn -Dmaven.test.skip=true clean install android:undeploy android:deploy android:run

deploy:
	scp target/me-biubiubiu-one-1.0.apk root@192.241.196.189:/usr/share/nginx/html/one.apk
