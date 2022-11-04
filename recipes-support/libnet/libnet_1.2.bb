SUMMARY = "A packet dissection and creation library"
# libnet at packetfactory.net is dead
HOMEPAGE = "https://github.com/libnet/libnet"
SECTION = "libs"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07f291bf6e78efa05cec668cf6a09acc"
DEPENDS = "libpcap"
# There are major API changes beween libnet v1.0 and libnet v1.1
PROVIDES = "libnet-1.2"

SRCREV = "deeeeaeb84f8bc5d2299913d4ccf53d0d4c26966"

SRC_URI = "git://github.com/libnet/libnet.git;branch=master;protocol=https"
SRC_URI[sha256sum] = "72c380785ad44183005e654b47cc12485ee0228d7fa6b0a87109ff7614be4a63"

S = "${WORKDIR}/git"

inherit autotools binconfig
