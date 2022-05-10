### Query Document Database
###
### Author: Beebe, Dean
### Course: CS5200
### Term: Summer 2022

# assumes that you have set up the database structure by running CreateFStruct.R

# Query Parameters (normally done via a user interface)

quarter <- "Q2"
year <- "2022"
customer <- "CarrePoint"

setLock <- function(customer, year, quarter) {
  file = ".lock"
  directory = paste("~/summer/5200/Module 1/docDB/reports", year, quarter, customer, sep="/")
  
  if(dir.exists(directory)) {
    setwd(directory)
  } else {
    dir.create(file.path(directory), recursive = TRUE)
    setwd(directory)
  }
  
  if(file.exists(file)) {
    print(".lock file already exists")
  } else {
    file.create(file)
    print("created .lock file!")
  }
}

relLock <- function(customer, year, quarter) {
  file <- ".lock"
  directory <- paste("~/summer/5200/Module 1/docDB/reports", year, quarter, customer, sep="/")
  setwd(directory)
  
  if(file.exists(file)) {
    file.remove(file)
    print("file successfully removed!")
  } else {
    print("no .lock file exists")
  }
  
}

checkLock <- function(customer, year, quarter) {
  file <- ".lock"
  directory <- paste("~/summer/5200/Module 1/docDB/reports", year, quarter, customer, sep="/")
  setwd(directory)
  
  if (file.exists(file)) {
    return (T)
  } else {
    return (F)
  }
}

genReportFName <- function(customer, year, quarter) {
  
  report <- paste(customer, year, quarter, "pdf", sep=".")
  
  return (report)
  
}

storeReport <- function(customer, year, quarter) {
  
  rtDir <- "~/summer/5200/Module 1"
  destDir <- paste(rtDir, "docDB", "reports", year, quarter, customer, sep="/")
  fileName <- genReportFName(customer, year, quarter)
  filePathName <- paste(destDir, fileName, sep="/")
  
  setwd(rtDir)
  
  if(file.exists(fileName)) {
    if (isTRUE(checkLock(customer, year, quarter))) {
      print("cannot modify, .lock file exists.")
    } else {
      file.copy(from=rtDir, to=filePathName, overwrite = T, recursive = FALSE)
    }
  } else {
    if (isTRUE(checkLock(customer, year, quarter))) {
      print("cannot modify, .lock file exists.")
    } else {
      pdf(file = fileName)
      file.copy(from=rtDir, to=filePathName, overwrite = T, recursive = FALSE)
    }
  }
  
  
}

#test functions here
relLock(customer,year, quarter)
setLock(customer, year, quarter)
report = genReportFName(customer, year, quarter)
print(report)
storeReport(customer, year, quarter)
