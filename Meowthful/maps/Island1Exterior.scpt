placeActor 2 6 6
setSpeech base 2 Beautiful day out, isn't it?
setSpeech success 2 Woohoo! Such a good day!
setSpeech fail 2 Oh, well. At least it's still a nice day.
setSpeech idle 2 I love when I get outside patrol on days like today.
placeActor 3 6 4
setSpeech base 3 Do you like the flowers? I planted them myself!
setSpeech success 3 The flowers bring me luck!
setSpeech fail 3 Oh! Down and out!
setSpeech idle 3 I don't care what the others say, this island is beautiful.

startProcedure -1 island2Telo
swapMap maps/Island2Exterior.WORLD 7 12 1
endProcedure

startProcedure 0 exit
if #islandKey = 1 island2Telo
if #islandKey = 3 island2Telo
endProcedure

startProcedure 1 exit1
event 0
endProcedure