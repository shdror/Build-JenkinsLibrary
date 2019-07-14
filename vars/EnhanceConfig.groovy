def call(Map cfg, body) {
  def config = cfg

  // Merging configs
  if( body in Closure ) {
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
  } else if( body in Map ) {
    Helper.mergeMaps(config, body)
  } else
    throw new MPLException("Unsupported MPL pipeline configuration type provided: ${body}")

  config
}