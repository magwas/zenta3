package org.rulez.magwas.errors;

public class ReportedError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportedError(final String message) {
		super(message);
	}

	public ReportedError(final String message, final String string) {
		super(String.format("%s: %s", message, string));
	}

}
