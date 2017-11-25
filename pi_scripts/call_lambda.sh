aws lambda invoke \
--invocation-type Event \
--function-name RateMyMeet \
--region eu-west-1 \
--payload $1 \
outputfile.txt
