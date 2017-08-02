# Start with the minial image as a base
require minimal-nilrt-image.bb

# Change description
DESCRIPTION = " \
    Fully-featured run-mode image for LabVIEW Comms. Includes drivers, \
    daemons, and other utilities required for RT targets to interact \
    with Comms. \
"

# Append additional software
IMAGE_INSTALL_NODEPS += " \
    lvrt \
    ni-datacomms-lvinterface \
    nitaglv \
    ni-nwstreams \
    ni-flexrio \
    ni-usrp-rio \
    mt \
"

IMAGE_INSTALL_NODEPS_remove_armv7a = " \
    ni-datacomms-lvinterface \
    ni-flexrio \
    ni-usrp-rio \
    mt \
"

IMAGE_INSTALL_append_n310 = " \
    u-boot \
    expand-disk \
"

IMAGE_INSTALL_NODEPS_append_x64 = " \
    ni-atca \
    ni-pxiplatformservices \
    ni-sync \
    ni-p2p \
"
