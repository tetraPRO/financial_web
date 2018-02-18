/*
 *Author     : tetraPRO( )
 *                   : Software Wizard
 *                 : Grand-Master_Philip Caputo
 *                 : **Fullstack Engineer**
 */

 function updateTime(){
                var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
                
                var currentTime = new Date();
                var day = days[currentTime.getDay()];
                var dayOfMonth = currentTime.getDate();
                var month = months[currentTime.getMonth()];
                
                var year = currentTime.getUTCFullYear();
                var hours = currentTime.getHours();
                var minutes = currentTime.getMinutes();
                if (minutes < 10){
                    minutes = "0" + minutes;
                }
                var t_str = hours + ":" + minutes;
                if(hours > 11){
                    t_str += "PM " + day + " " + " Day "+ dayOfMonth +" of " + month + " " + year;
                } else {
                    t_str += "AM " + day + " " + " Day "+ dayOfMonth +" of " + month + " " + year;
                }
                document.getElementById('time_stamp').innerHTML = t_str;
            }
            setInterval(updateTime, 1000);
