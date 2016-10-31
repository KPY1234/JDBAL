package jdbal.action;

interface QueryBuilder {

	public String selectQueryBuild(Selection se);
	public String insertQueryBuild(Insertion insert);
	public String updateQueryBuild(Update update);
	public String deleteQueryBuild(Deletion delete);
	public String createTableQueryBuild(TableCreator tc);
}
