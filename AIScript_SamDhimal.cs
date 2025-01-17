﻿using UnityEngine;
using System.Collections;

public class AIScript_SamDhimal : MonoBehaviour {

    public CharacterScript mainScript;

    public float[] bombSpeeds; // Speed that bomb is moving on
    public float[] buttonCooldowns;
    public float playerSpeed;// Speed that player is moving on
    public int[] beltDirections; // which direction belt is moving on
    public float[] buttonLocations; //Location of the button in an array

    //initializing Variables
	public float opponentLocation; // Location of an opponent
	public float characterLocation; // Location of the AI
	public float[] bombDistance; // Button and Bomb distance


	// Use this for initialization
	void Start () {
        mainScript = GetComponent<CharacterScript>();

        if (mainScript == null)
        {
            print("No CharacterScript found on " + gameObject.name);
            this.enabled = false;
        }

        buttonLocations = mainScript.getButtonLocations();

        playerSpeed = mainScript.getPlayerSpeed();


	}



	// Update is called once per frame
	void Update () {

        buttonCooldowns = mainScript.getButtonCooldowns();
        beltDirections = mainScript.getBeltDirections();

        //Your AI code goes here
		opponentLocation = mainScript.getOpponentLocation();
        characterLocation = mainScript.getCharacterLocation();
		bombDistance = mainScript.getBombDistances();	
        bombSpeeds = mainScript.getBombSpeeds();
        playerSpeed = mainScript.getPlayerSpeed();
        buttonLocations = mainScript.getButtonLocations();

        float charDistanceToOpponent = Mathf.Abs(characterLocation-opponentLocation);//how far the character is from opponent.


        float [] charDisFromBomb = new float[8];
        float [] opponentDisFromBomb = new float[8];
        float [] bombTime = new float[8];
        float [] characterTime = new float[8];
        float [] opponentTime = new float[8];
        float minimumDistance = 1000;
        int minimumIndex = 0;
        

        for (int i = 0; i < buttonLocations.Length; i++) {
            
            //Calculating character distance from bomb
            charDisFromBomb[i] = Mathf.Abs(characterLocation - buttonLocations[i]);

            if ((beltDirections[i] == -1) || (beltDirections[i] == 0)) {//Buttons that are engaged and bomb coming towards the character or bomb not engaged.

                //Calculating Bomb Time
                bombTime[i] = (bombDistance[i]/bombSpeeds[i]);
                //Calculating character time to get to the button.
                characterTime[i] = Mathf.Abs((characterLocation - buttonLocations[i])/playerSpeed);
                opponentTime[i] = Mathf.Abs((opponentLocation - buttonLocations[i])/playerSpeed);

                if((characterTime[i] < bombTime[i]) && (characterTime[i] > buttonCooldowns[i]) && (charDisFromBomb[i] < minimumDistance)) {
                    
                    minimumIndex = i;
                    minimumDistance = charDisFromBomb[i];
                }

            }

        }    
            if (buttonLocations[minimumIndex] < characterLocation) {
                
                mainScript.push();
                mainScript.moveDown();
            }
            else {
                mainScript.push();
                mainScript.moveUp(); 
            }
   }
}
