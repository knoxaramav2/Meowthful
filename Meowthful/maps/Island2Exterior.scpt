placeActor 10 8 2
placeActor 11 4 2
placeActor 12 9 10

startProcedure -1 island1Telo
swapMap maps/Island1Exterior.WORLD 7 13 1
endProcedure

startProcedure -2 island3Telo
swapMap maps/Island3Exterior.WORLD 12 13 1
endProcedure

startProcedure 0 exit
if #islandKey = 2 island3Telo
if #islandKey = 1 island1Telo
endProcedure

startProcedure 1 exit1
subProc 0
endProcedure