# Automatic-U-Pass-BC-Renewer!
[![Monthly U-Pass Renewal](https://github.com/SuperRitchie/Automatic-U-Pass-BC-Renewer/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/SuperRitchie/Automatic-U-Pass-BC-Renewer/actions/workflows/main.yml)\
This program automatically renews your U-Pass BC on the 16th of every month at noon! (includes 2FA support!)

# Usage
The following are easy instructions to use this program...

# 1 Fork this repository

# 2 Replace the 2FA Secret Key in TOTPGenerator.java with your own

# 3 Create two repository secrets; one with your username and the other with your password. The secret names by default are SFU_USERID and SFU_PASSWORD but you can change this in main.yaml (don't forget to change the env variable call in Main.java if you do this)

# 4 and that's it! Your U-Pass will now automatically renew on the 16<sup>th</sup> of every month! (currently only SFU is supported)
