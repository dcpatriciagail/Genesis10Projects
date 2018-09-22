function play () {	
	var startbet = document.forms["luckyForm"]["startbet"].value;
	if (startbet == 0 || isNaN(startbet)) {
        alert ("Provide Starting Bet");
        document.getElementById("results").style.display = "none";
        return false;
	}
	
	var dice1 = 0;
	var dice2 = 0;
    var diceTotal = 0;
    var dollar = startbet
    var numberofRolls = 0;
    var amountWon = 0;
	var maxDollar = 0;
    var highestRoll = 0;
    
	do{
		numberofRolls++
		dice1 = Math.floor(Math.random()*6)+1;
		dice2 = Math.floor(Math.random()*6)+1;
		diceTotal = dice1 + dice2;
			
		if(diceTotal == 7){
            dollar += 4;
            amountWon += 4;					
		}
		else{
            dollar--;
            amountWon--;
        }
                
        //This is where I check the if current Total is the highest
        if(maxDollar < amountWon){
            maxDollar = amountWon
            highestRoll = numberofRolls
        }
			
			
        }while(dollar>0);
    
    maxDollar = parseFloat(Math.round(maxDollar * 100) / 100).toFixed(2);
    startbet = parseFloat(Math.round(startbet * 100) / 100).toFixed(2);

	document.getElementById("results").style.display = "block";
	document.getElementById("playButton").innerText="Play";
	document.getElementById("firstBet").innerText = "$" + startbet;
	document.getElementById("resultrollsBroke").innerText= numberofRolls;
	document.getElementById("resultsmaxAmount").innerText= "$" + maxDollar;
	document.getElementById("resultrollCount").innerText= highestRoll;
	return false;
}