all:
	mvn -Dmaven.test.skip=true clean install android:undeploy android:deploy android:run; if [ $$? -eq 0 ]; then terminal-notifier -message "Build success" -title "Result" -sound Submarine; else terminal-notifier -message "Build failed" -title "Result" sound default; fi

deploy:
	scp target/me-biubiubiu-one-1.0.apk root@192.241.196.189:/usr/share/nginx/html/one.apk
