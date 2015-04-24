placeActor 9 7 3
setSpeech base 9 Another hopeful grunt, eh? You want off this Island so bad? You think Island Two will be so much the better? Fine! But first, you go through me!
setSpeech success 9 Looks like you don't want out of here so bad after all.
setSpeech fail 9 ...Fine. You want to move on so bad? Then go.
setSpeech idle 9 Get out of here. See if Island Two is everything you thought it would be.

startProcedure -1 gift
givePokemon 0 pikachu
setVar islandKey 1
endProcedure

battleWin 9 gift