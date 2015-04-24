placeActor 18 7 3

setSpeech base 18 Not many get to me, you know. Even less leave.
setSpeech success 18 Get out of my sight.
setSpeech fail 18 ... I lost ...
setSpeech idle 18 You're a tough one, alright

startProcedure -1 gift
givePokemon 0 nidoking
setVar islandKey 2
endProcedure

battleWin 18 gift