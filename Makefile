all:
	mvn -Dmaven.test.skip=true install android:undeploy android:deploy android:run; if [ $$? -eq 0 ]; then terminal-notifier -message "Build success" -title "Result" -sound Submarine; else terminal-notifier -message "Build failed" -title "Result" sound default; fi
