console.log("script load");

let currentTheme=getTheme();
changeTheme();

function changeTheme(){
    document.querySelector('html').classList.add(currentTheme)

    //set listner to change theme button
   const changeThemeButton =  document.querySelector('#theme_change_button')
   changeThemeButton.addEventListener('click',(event) => {
    console.log("change theme button clicked")
    document.querySelector('html').classList.remove(currentTheme)
    if(currentTheme=="dark"){
        //theme to light
        currentTheme="light";
    }else{
        //theme to dark
        currentTheme="dark";
    }
    //localStorage update
    setTheme(currentTheme);
    //set the current theme
    document.querySelector('html').classList.add(currentTheme)
   });
}

//Set theme to local storage

function setTheme(theme){

    localStorage.setTheme("theme",theme)
}
//get theme from local storage

function getTheme(){
    let theme=localStorage.getItem("theme")
    if(theme){
        return theme;
 }else return "light";
}