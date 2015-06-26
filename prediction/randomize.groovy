java.util.Random r = new java.util.Random()

payload.put("LastTradePriceOnly", payload.get("LastTradePriceOnly") + (payload.get("LastTradePriceOnly")*r.nextGaussian())/4)
payload.put("DaysLow", payload.get("DaysLow") + (payload.get("DaysLow")*r.nextGaussian()))
payload.put("DaysHigh", payload.get("DaysHigh") + (payload.get("DaysHigh")*r.nextGaussian()))
payload.put("entryTimestamp", System.nanoTime())

return payload
