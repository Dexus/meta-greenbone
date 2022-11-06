DESCRIPTION = "Greenbone Master Package Groups"

inherit packagegroup

PACKAGES = "\
    ${PN}-gsad \
    ${PN}-gvmd \
    "

RDEPENDS:${PN}-gsad = "\
    gsa \
    gsad \
"

RDEPENDS:${PN}-gvmd = "\
    gvm-libs \
"
