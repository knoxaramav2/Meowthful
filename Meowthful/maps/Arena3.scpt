placeActor 26 7 2

setSpeech base 26 You believe you can defeat me? Go ahead and try.
setSpeech success 26 Get out of my sight.
setSpeech fail 26 ... This empire falls...
setSpeech idle 26 Congratulations. You get to deal with the mob now.

startProcedure -1 gift
givePokemon 0 kadabra
setVar islandKey 3
endProcedure

battleWin 26 gift