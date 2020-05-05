word=$1
outputFilename=$2

if [ "$word" = "" ]; then
	exit
fi

if [ "$outputFilename" = "" ]; then
	outputFilename=$word.mp3
fi

if [ -f $outputFilename ]; then
	echo already exists $word $outputFilename
	exit
fi

mainUrl=https://www.wordreference.com
page="${mainUrl}/enko/$word"
uri=$(http $page | grep '<audio' | sed 's/<source/\n/g' | grep src | head -1 | sed -e "s/^.*src='//" -e "s/' .*$//")
if [ "$uri" = "" ]; then
	echo error $word
	exit
fi

resourceUrl=${mainUrl}${uri}
wget $resourceUrl -O $outputFilename

